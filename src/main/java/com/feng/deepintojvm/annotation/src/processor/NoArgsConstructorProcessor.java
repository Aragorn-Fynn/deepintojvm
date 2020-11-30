/**
 * 源码来自：https://liuyehcf.github.io/2018/02/02/Java-JSR-269-%E6%8F%92%E5%85%A5%E5%BC%8F%E6%B3%A8%E8%A7%A3%E5%A4%84%E7%90%86%E5%99%A8/
 */
package com.feng.deepintojvm.annotation.src.processor;

import com.feng.deepintojvm.annotation.src.annotation.NoArgsConstructor;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.List;

import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

import static com.feng.deepintojvm.annotation.src.processor.ProcessUtil.CONSTRUCTOR_NAME;
import static com.feng.deepintojvm.annotation.src.processor.ProcessUtil.hasNoArgsConstructor;

@SupportedAnnotationTypes("org.liuyehcf.annotation.source.annotation.NoArgsConstructor")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NoArgsConstructorProcessor extends BaseProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //首先获取被NoArgsConstructor注解标记的元素
        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(NoArgsConstructor.class);

        set.forEach(element -> {

            //获取当前元素的JCTree对象
            JCTree jcTree = trees.getTree(element);

            //JCTree利用的是访问者模式，将数据与数据的处理进行解耦，TreeTranslator就是访问者，这里我们重写访问类时的逻辑
            jcTree.accept(new TreeTranslator() {
                @Override
                public void visitClassDef(JCTree.JCClassDecl jcClass) {
                    messager.printMessage(Diagnostic.Kind.NOTE, "@NoArgsConstructor process [" + jcClass.name.toString() + "] begin!");

                    //添加无参构造方法
                    if (!hasNoArgsConstructor(jcClass)) {
                        jcClass.defs = jcClass.defs.append(
                                createNoArgsConstructor()
                        );
                    }

                    messager.printMessage(Diagnostic.Kind.NOTE, "@NoArgsConstructor process [" + jcClass.name.toString() + "] end!");
                }
            });
        });

        return true;
    }

    /**
     * 创建无参数构造方法
     *
     * @return 无参构造方法语法树节点
     */
    private JCTree.JCMethodDecl createNoArgsConstructor() {

        JCTree.JCBlock jcBlock = treeMaker.Block(
                0 //访问标志
                , List.nil() //所有的语句
        );

        return treeMaker.MethodDef(
                treeMaker.Modifiers(Flags.PUBLIC), //访问标志
                names.fromString(CONSTRUCTOR_NAME), //名字
                treeMaker.TypeIdent(TypeTag.VOID), //返回类型
                List.nil(), //泛型形参列表
                List.nil(), //参数列表
                List.nil(), //异常列表
                jcBlock, //方法体
                null //默认方法（可能是interface中的那个default）
        );
    }
}
