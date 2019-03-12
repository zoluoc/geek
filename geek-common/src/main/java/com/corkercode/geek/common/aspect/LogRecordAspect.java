package com.corkercode.geek.common.aspect;

import com.corkercode.geek.common.util.AbstractLogUtil;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * <p>
 * Controller统一切点日志处理
 * <p/>
 *
 * @author: zoluo
 * @date: 2019/3/13 02:07
 */
@Aspect
public class LogRecordAspect {

    @Pointcut("execution(public * com.corkercode.geek.*.controller.*Controller.*(..))")
    @SuppressWarnings("EmptyMethod")
    public void pointCut() {
    }

    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfterReturning(Object ret) {
        AbstractLogUtil.doAfterReturning(ret);
    }

}
