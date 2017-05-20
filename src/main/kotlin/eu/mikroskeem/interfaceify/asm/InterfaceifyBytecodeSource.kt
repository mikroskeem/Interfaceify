package eu.mikroskeem.interfaceify.asm

import eu.mikroskeem.interfaceify.InterfaceifySource
import eu.mikroskeem.interfaceify.MethodInfo
import org.objectweb.asm.ClassReader
import java.io.ByteArrayOutputStream
import java.net.URLClassLoader

/**
 * @author Mark Vainomaa
 */
class InterfaceifyBytecodeSource : InterfaceifySource {
    override fun from(clazz: Class<*>): List<MethodInfo> {
        // Get class bytecode
        val ucl = this.javaClass.classLoader as URLClassLoader
        val baos = ByteArrayOutputStream()
        ucl.getResourceAsStream(clazz.name.replace('.', '/') + ".class").use { it.copyTo(baos) }
        return from(baos.toByteArray())
    }

    override fun from(clazzData: ByteArray): List<MethodInfo> {
        val methods = ArrayList<MethodInfo>()

        // Apply Interfaceify class visitor
        val cr = ClassReader(clazzData)
        cr.accept(InterfaceifyClassVisitor(methods), 0)

        // Return harvested (:P) methods
        return methods
    }
}