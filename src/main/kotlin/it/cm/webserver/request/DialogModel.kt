package it.cm.webserver.request

/**
 * A data class representing a model for a dialog.
 *
 * @property title The title of the dialog.
 * @property description The description of the dialog.
 */
data class DialogModel(
    val title: String
    , val description: String
)