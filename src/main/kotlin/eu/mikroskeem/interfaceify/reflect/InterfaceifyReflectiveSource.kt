package eu.mikroskeem.interfaceify.reflect

import eu.mikroskeem.interfaceify.InterfaceifySource
import eu.mikroskeem.interfaceify.MethodInfo

/**
 * @author Mark Vainomaa
 */
class InterfaceifyReflectiveSource : InterfaceifySource {
    override fun from(clazz: Class<*>): List<MethodInfo> {
        val methods = ArrayList<MethodInfo>()

        // Iterate through class methods
        clazz.declaredMethods.forEach { method ->
            val params = ArrayList<Pair<Class<*>, String>>()
            var counter = 0
            method.parameters.forEach {
                params.add(Pair(it.type, "p$counter"))
                counter++
            }
            methods.add(MethodInfo(method.name, params, method.returnType))
        }

        return methods
    }

    override fun from(clazzData: ByteArray): List<MethodInfo> {
        TODO("not implemented")
    }
}