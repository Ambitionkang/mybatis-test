package plugin;

import entity.BaseEntity;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Date;
import java.util.Properties;

/**
 * 插件签名
 * 告诉Mybatis拦截哪个对象（四大对象之一）的哪个方法
 */
@Intercepts(
        @Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class})
)
public class MyPlugin implements Interceptor {
    /**
     * 拦截目标方法的执行，可以像aop一样在方法执行前后植入其他代码实现扩展
     * @param invocation
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("方法执行前："+invocation);
        Object[] args = invocation.getArgs();
        if(args[1] instanceof BaseEntity){
            BaseEntity entity = (BaseEntity) args[1];
            entity.setCreator("拦截器");
            entity.setCtime(new Date());
        }
        Object obj =  invocation.proceed();
        System.out.println("方法执行后"+obj);
        return obj;
    }

    /**
     * 包装感兴趣的目标对象
     * 为目标对象创建一个代理对象
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        // 可以自己用java动态代理或者cglib实现代理
        // 也可以借助Mybatis自己提供的一个方法Plugin.wrap为目标对象创建代理
        Object obj = Plugin.wrap(target,this);
        // 返回为当前target创建的动态代理
        return obj;
    }

    /**
     * 将插件注册时配置的参数注入到拦截器中
     * @param properties
     */
    public void setProperties(Properties properties) {
        System.out.println("插件的配置信息："+properties);
    }
}
