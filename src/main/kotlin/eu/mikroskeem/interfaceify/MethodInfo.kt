package eu.mikroskeem.interfaceify

/**
 * @author Mark Vainomaa
 */
data class MethodInfo(
    val name: String,
    val accepts: List<Pair<Class<*>, String>>,
    val returns: Class<*>
)