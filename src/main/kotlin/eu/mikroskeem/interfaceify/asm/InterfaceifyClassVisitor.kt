package eu.mikroskeem.interfaceify.asm

import eu.mikroskeem.interfaceify.MethodInfo
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type

/**
 * @author Mark Vainomaa
 */
class InterfaceifyClassVisitor(private val methodInfo: MutableList<MethodInfo>) : ClassVisitor(Opcodes.ASM5) {
    override fun visitMethod(access: Int, name: String, desc: String, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        if(name == "<clinit>" || name == "<init>") return null
        parseDescriptor(desc).run {
            methodInfo.add(MethodInfo(name, first, second))
        }
        return super.visitMethod(access, name, desc, signature, exceptions)
    }

    private fun parseDescriptor(descriptor: String) : Pair<List<Pair<Class<*>, String>>, Class<*>> {
        var counter = 0
        val returnType = getClass(Type.getReturnType(descriptor).className)
        val params = ArrayList<Pair<Class<*>, String>>()
        Type.getArgumentTypes(descriptor).forEach {
            params.add(Pair(getClass(it.className), "p$counter"))
            counter++
        }

        return Pair(params, returnType)
    }

    // EEEK???
    private fun getClass(name : String) : Class<*> {
        var clazz : Class<*>
        try {
            clazz = Class.forName(name)
        } catch (e : ClassNotFoundException) {
            // kys
            val method = Class::class.java.getDeclaredMethod("getPrimitiveClass", String::class.java)
            method.isAccessible = true
            clazz = method.invoke(null, name) as Class<*>
        }
        return clazz
    }
}