/**
 * 源码来自：https://liuyehcf.github.io/2018/02/02/Java-JSR-269-%E6%8F%92%E5%85%A5%E5%BC%8F%E6%B3%A8%E8%A7%A3%E5%A4%84%E7%90%86%E5%99%A8/
 */
package com.feng.deepintojvm.annotation.src.processor;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;

public abstract class BaseProcessor extends AbstractProcessor {
    /**
     * 用于在编译器打印消息的组件
     */
    Messager messager;

    /**
     * 语法树
     */
    JavacTrees trees;

    /**
     * 用来构造语法树节点
     */
    TreeMaker treeMaker;

    /**
     * 用于创建标识符的对象
     */
    Names names;

    /**
     * 获取一些注解处理器执行处理逻辑时需要用到的一些关键对象
     *
     * @param processingEnv 处理环境
     */
    @Override
    public final synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }
}
