package com.yc.springframework.context;

import com.yc.springframework.stereotype.*;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

/**
 * @program: testspring
 * @description:
 * @author: 张韬
 * @create: 2021-04-05 11:41
 */
public class MyAnnotationConfigApplicationContext implements MyApplicationContext {
    private Map<String, Object> beanMap = new HashMap<String, Object>();


    public MyAnnotationConfigApplicationContext(Class<?>... componentClasses) {
        try {
            register(componentClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void register(Class<?>[] componentClasses) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, ClassNotFoundException {
        if (componentClasses == null || componentClasses.length <= 0) {
            throw new RuntimeException("没有指定配置类");
        }
        for (Class cl : componentClasses) {
            //源码1：实现IOC

            if (!cl.isAnnotationPresent(MyConfiguration.class)) {
                continue;
            }
            String[] basePackages = getAppConfigBasePackages(cl);
            if (cl.isAnnotationPresent(MyComponentScan.class)) {
                MyComponentScan mcs = (MyComponentScan) cl.getAnnotation(MyComponentScan.class);
                if (mcs.basePackages() != null && mcs.basePackages().length > 0) {
                    basePackages = mcs.basePackages();
                }

            }
            //处理@MyBena的情况
            Object obj = cl.getDeclaredConstructor().newInstance();
            handAtMyBean(cl, obj);
            //处理  basePackages  基础包下的所有托管bean
            for (String basePackage : basePackages) {
                scanPackagaeAndSubPackageClassesckageClasses(basePackage);
            }
            //继续托管其他bean
            handleManagedBean();
            //版本2:   循环  beanMap中的每个bean , 找到它们每个类中的每个由@Autowired @Resource注解的方法以实现di,
            handleDi(beanMap);

        }

    }

    /**
     * 循环  beanMap中的每个bean , 找到它们每个类中的每个由@Autowired @Resource注解的方法以实现di,
     */
    private void handleDi(Map<String, Object> beanMap) throws InvocationTargetException, IllegalAccessException {
        Collection<Object> objectCollection = beanMap.values();
        for (Object obj : objectCollection) {
            Class cls = obj.getClass();
            Method[] ms = cls.getDeclaredMethods();
            for (Method m : ms) {
                if (m.isAnnotationPresent(MyAutowired.class) && m.getName().startsWith("set")) {
                    invokeAutowiredMethod(m, obj);
                } else if (m.isAnnotationPresent(MyResource.class) && m.getName().startsWith("set")) {
                    invokeResourceMethod(m, obj);
                }
            }
            Field[] fs = cls.getDeclaredFields();
            for (Field field : fs) {
                if (field.isAnnotationPresent(MyAutowired.class)) {

                } else if (field.isAnnotationPresent(MyResource.class)) {

                }
            }
        }
    }

    private void invokeResourceMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1. 取出  MyResource中的name属性值 ,当成   beanId
        MyResource mr = m.getAnnotation(MyResource.class);
        String beanId = mr.name();
        //2、如果没有,则取出m 的方法中参数的类型名,改写首字母小写  当成beanId
        if (beanId == null || beanId.equalsIgnoreCase("")) {
            String pname = m.getParameterTypes()[0].getSimpleName();
            beanId = pname.substring(0, 1).toLowerCase() + pname.substring(1);
        }
        //3、从beanMap中取出
        Object o = beanMap.get(beanId);
        //4、invoke
        m.invoke(obj, o);
    }

    private void invokeAutowiredMethod(Method m, Object obj) throws InvocationTargetException, IllegalAccessException {
        //1、取出m的参数的类型
        Class typeClass = m.getParameterTypes()[0];
        //2、从beanMap中循环所有的 objet
        Set<String> keys = beanMap.keySet();
        for (String key : keys) {
            //3、如果是，则从beanMap取出
            Object o = beanMap.get(key);
            //4、判断 这些object 是否为   参数类型的实例  instanceof
            Class<?>[] interfaces = o.getClass().getInterfaces();
            for (Class c : interfaces) {
                System.out.println(c.getName() + "\t" + typeClass);
                if (c == typeClass) {
                    m.invoke(obj, o);
                    break;
                }
            }
        }
    }

    /**
     * 处理managedBeanClasses 所有的Class类，筛选出所有的@Component @Service, @Repository的类，并实例化，存到 beanMap中
     */
    private void handleManagedBean() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        for (Class c : managedBeanClasses) {
            if (c.isAnnotationPresent(MyComponet.class)) {
                saveManagedBean(c);
            } else if (c.isAnnotationPresent(MyService.class)) {
                saveManagedBean(c);
            } else if (c.isAnnotationPresent(MyRepository.class)) {
                saveManagedBean(c);
            } else if (c.isAnnotationPresent(MyController.class)) {
                saveManagedBean(c);
            } else {
                continue;
            }
        }
    }

    private Set<Class> managedBeanClasses = new HashSet<Class>();

    private void saveManagedBean(Class c) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Object o = c.newInstance();
        handlePostConstruct(o, c);
        String beanId = c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
        beanMap.put(beanId, o);
    }

    /**
     * 处理一个Bean中的 @MyPostConstruct对应的方法
     *
     * @param o
     * @param cls
     */
    private void handlePostConstruct(Object o, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            if (m.isAnnotationPresent(MyPostConstruct.class)) {
                m.invoke(o);
            }
        }
    }

    /**
     * 扫描包和子包
     *
     * @param basePackage
     * @throws IOException
     */
    private void scanPackagaeAndSubPackageClassesckageClasses(String basePackage) throws IOException, ClassNotFoundException {
        //把点替换成/
        String packagePath = basePackage.replaceAll("\\.", "/");
        System.out.println("扫描包路径：" + basePackage + "，替换后：" + packagePath);
        Enumeration<URL> files = Thread.currentThread().getContextClassLoader().getResources(packagePath);
        while (files.hasMoreElements()) {
            URL url = files.nextElement();
            System.out.println("配置的扫描路径为：" + url.getFile());
            //递归目录,查找  .class文件
            findClassesInPackages(url.getFile(), basePackage);    //第二个参数：com.yc.bean
        }
    }

    /**
     * 查找  file 下面及子包所有的要托管的class,存到一个Set(  managedBeanClasses  )中.
     *
     * @param file
     * @param basePackage
     */
    private void findClassesInPackages(String file, String basePackage) throws ClassNotFoundException {
        File f = new File(file);
        File[] classFiles = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".class") || file.isDirectory();
            }
        });
        // System.out.println(classFiles);
        for (File cf : classFiles) {
            if (cf.isDirectory()) {
                //如果目录,则递归
                //拼接子目录
                basePackage += "." + cf.getName().substring(cf.getName().lastIndexOf("/") + 1);
                findClassesInPackages(cf.getAbsolutePath(), basePackage);

            } else {
                //加载 cf  作为  class文件
                URL[] urls = new URL[]{};
                URLClassLoader ucl = new URLClassLoader(urls);
                //com.yc.bean.Hello.class  ->  com.yc.bean.Hello
                Class c = ucl.loadClass(basePackage + "." + cf.getName().replace(".class", ""));
                managedBeanClasses.add(c);

            }
        }
    }

    /**
     * 处理 MyAppConfig配置类中的 @Bean注解，完成 IOC操作
     *
     * @param cls
     * @param obj
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handAtMyBean(Class cls, Object obj) throws InvocationTargetException, IllegalAccessException {

        //1、获取cls中所有的method
        Method[] ms = cls.getDeclaredMethods();
        //2、循环、判断 每个method上是否有@MyBean注解
        for (Method m : ms) {
            if (m.isAnnotationPresent(MyBean.class)) {
                //3、有，则invoke它，它有返回值，将返回值存到benaMap
                Object o = m.invoke(obj);
                //加入处理  @MyBean注解对应的放啊所实例化的类中的@MyPostConstruct对应的方法
                handlPostConstruct(o, o.getClass()); //o在这里指HelloWorld对象  o.getClass()它的反射对象
                beanMap.put(m.getName(), o);
            }
        }

    }

    /**
     * 处理一个Bean中的   @MyPostConstruct对应的方法
     *
     * @param o
     * @param cls
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void handlPostConstruct(Object o, Class<?> cls) throws InvocationTargetException, IllegalAccessException {
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) {
            if (m.isAnnotationPresent(MyPostConstruct.class)) {
                m.invoke(o);
            }
        }
    }


    /**
     * 获取当前     AppConfig的路径
     *
     * @param cl
     * @return
     */
    private String[] getAppConfigBasePackages(Class cl) {
        String[] paths = new String[1];
        paths[0] = cl.getPackage().getName();
        return paths;
    }


    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }


}
