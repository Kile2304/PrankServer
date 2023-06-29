package it.cm.webserver.util

import java.io.BufferedReader
import java.io.InputStreamReader


// Verrà integrato nella versione 1.4-SNAPSHOT

/**
 * Checks if the specified port is blocked by the firewall.
 *
 * @param port The port to check.
 * @return `true` if the port is blocked, `false` otherwise.
 */
fun isFirewallPortBlocked(port: Int): Boolean {
    val process = Runtime.getRuntime().exec("netsh advfirewall firewall show rule name=all")
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    var line: String?

    var regola = FirewallRule()
    while (reader.readLine().also { line = it } != null) {
        when {
            line!!.startsWith("Nome regola:") -> {
                if (regola.nomeRegola != null) {
                    if (regola.attivata &&
                        (regola.azione.equals("Block", true) || regola.azione.equals("Blocca", true))
                        && regola.localPort?.matches(Regex("\\d+")) == true
                        && regola.localPort?.toInt() == port
                        && regola.direzione.equals("In", true)
                        && regola.protocollo.equals("TCP", true)
                    ) {
                        println("Porta del firewall bloccata in ingresso: $port")
                        return true
                    }
                }
                regola = FirewallRule() // Crea una nuova regola quando inizia una nuova regola
                regola.nomeRegola = line!!.substringAfter("Nome regola:")
            }
            line!!.startsWith("Attivata:") -> regola.attivata = (line!!.substringAfter("Attivata:").trim().contains("S", true)) ?: false
            line!!.startsWith("Direzione:") -> regola.direzione = line!!.substringAfter("Direzione:").trim()
            line!!.startsWith("Profili:") -> regola.profili = line!!.substringAfter("Profili:").trim().split(",")
            line!!.startsWith("Azione:") -> regola.azione = line!!.substringAfter("Azione:").trim()
            line!!.startsWith("Protocollo:") -> regola.protocollo = line!!.substringAfter("Protocollo:").trim()
            line!!.startsWith("LocalPort:") -> regola.localPort = line!!.substringAfter("LocalPort:").trim()
            line!!.startsWith("Attraversamento confini:") -> regola.attraversamentoConfini = line!!.substringAfter("Attraversamento confini:").trim()
            line!!.startsWith("RemotePort:") -> regola.remotePort = line!!.substringAfter("RemotePort:").trim()
            line!!.startsWith("LocalIP:") -> regola.localIP = line!!.substringAfter("LocalIP:").trim()
            line!!.startsWith("RemoteIP:") -> regola.remoteIP = line!!.substringAfter("RemoteIP:").trim()
            line!!.startsWith("Raggruppamento:") -> regola.raggruppamento = line!!.substringAfter("Raggruppamento:").trim()
        }
    }
    return false
}

/**
 * A class representing a firewall rule.
 *
 * @param nomeRegola The name of the firewall rule.
 * @param attivata Whether the firewall rule is activated or not.
 * @param direzione The direction of the firewall rule.
 * @param profili The list of profiles associated with the firewall rule.
 * @param azione The action to be taken by the firewall rule.
 * @param protocollo The protocol used by the firewall rule.
 * @param localPort The local port used by the firewall rule.
 * @param attraversamentoConfini The crossing boundaries property of the firewall rule.
 * @param remotePort The remote port used by the firewall rule.
 * @param localIP The local IP used by the firewall rule.
 * @param remoteIP The remote IP used by the firewall rule.
 * @param raggruppamento The grouping property of the firewall rule.
 */
data class FirewallRule(
    var nomeRegola: String? = null,
    var attivata: Boolean = false,
    var direzione: String? = null,
    var profili: List<String>? = null,
    var azione: String? = null,
    var protocollo: String? = null,
    var localPort: String? = null,
    var attraversamentoConfini: String? = null,
    var remotePort: String? = null,
    var localIP: String? = null,
    var remoteIP: String? = null,
    var raggruppamento: String? = null
)