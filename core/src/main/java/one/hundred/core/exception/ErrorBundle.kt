
package one.hundred.core.exception

/**
 * Interface to represent a wrapper around an [Exception] to manage errors.
 */
interface ErrorBundle {
    val exception: Exception

    val errorMessage: String
}
