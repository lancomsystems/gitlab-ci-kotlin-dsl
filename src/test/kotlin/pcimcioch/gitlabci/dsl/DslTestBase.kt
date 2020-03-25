package pcimcioch.gitlabci.dsl

import kotlinx.serialization.SerializationStrategy
import org.assertj.core.api.Assertions.assertThat
import java.io.StringWriter

internal abstract class DslTestBase {

    internal fun <T : DslBase> assertDsl(strategy: SerializationStrategy<T>, testee: T, expectedYaml: String, vararg validationErrors: String) {
        val yaml = serialize(strategy, testee)
        assertThat(yaml).isEqualTo(expectedYaml)

        val errors = mutableListOf<String>()
        testee.validate(errors)
        assertThat(errors).containsExactlyInAnyOrder(*validationErrors)
    }

    private fun <T : DslBase> serialize(strategy: SerializationStrategy<T>, value: T): String {
        val writer = StringWriter()
        writer.use { serializeToYaml(strategy, value, writer) }
        return writer.toString()
    }
}