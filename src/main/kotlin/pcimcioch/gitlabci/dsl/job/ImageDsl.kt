package pcimcioch.gitlabci.dsl.job

import kotlinx.serialization.Serializable
import pcimcioch.gitlabci.dsl.DslBase
import pcimcioch.gitlabci.dsl.DslBase.Companion.addError
import pcimcioch.gitlabci.dsl.GitlabCiDslMarker
import pcimcioch.gitlabci.dsl.isEmpty

@GitlabCiDslMarker
@Serializable
class ImageDsl(
        var name: String? = null
) : DslBase {
    var entrypoint: List<String>? = null

    fun entrypoint(vararg elements: String) = entrypoint(elements.toList())
    fun entrypoint(elements: Iterable<String>) {
        entrypoint = elements.toList()
    }

    override fun validate(errors: MutableList<String>) {
        addError(errors, isEmpty(name), "[image] name '$name' is incorrect")
    }
}

fun image(name: String) = ImageDsl(name)
fun image(block: ImageDsl.() -> Unit) = ImageDsl().apply(block)
fun image(name: String, block: ImageDsl.() -> Unit) = ImageDsl(name).apply(block)
