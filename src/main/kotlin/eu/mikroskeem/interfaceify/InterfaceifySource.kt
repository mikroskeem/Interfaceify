package eu.mikroskeem.interfaceify

/**
 * @author Mark Vainomaa
 */
interface InterfaceifySource {
    fun from(clazz: Class<*>) : List<MethodInfo>
    fun from(clazzData: ByteArray) : List<MethodInfo>
}