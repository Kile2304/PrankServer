# PrankServer
Questo WebServer prova a collegarsi in una delle seguenti porte:
- 6050
- 6040
- 6060
- 5050
- 5040
- 5060

Se nessuno delle seguenti porte è disponibile, l'applicativo non parte.

## Endpoint

### Autenticati

#### /version *(GET)*
Ritorna il numero di versione corrente dell'applicativo.
```shell
curl -X GET -i http://<ip>:6050/version -u <username>:<password>
```

#### /ping *(GET)*
Semplice test per controllare se il server è su.
```shell
curl -X GET -i http://<ip>:6050/ping -u <username>:<password>
```

#### /shutdown *(GET)*
Chiude del server.
```shell
curl -X GET -i http://<ip>:6050/shutdown -u <username>:<password>
```

#### /browse *(POST)*
Permette l'apertura del browser all'URL passato come parametro.<br/>
L'url deve essere passato nel body.
```shell
curl -X POST -i http://<ip>:6050/browse -d <url> -u <username>:<password>
```

#### /popup *(POST)*
Permette la visualizzazione di un popup custom che rimane sempre in
primo piano.
Il body che deve essere passato è il seguente:<br/>
**DialogModel:**
```json
{
  "title": "Titolo del popup",
  "description": "Descrizione del popup"
}
```
```shell
curl -X POST -i http://<ip>:6050/popup -d <json> -u <username>:<password>
```

#### /move_mouse *(GET)*
Effettua il movimento del mouse in 5 posizioni diverse con delay da 3 a
6 secondi dopo ogni movimento.
```shell
curl -X GET -i http://<ip>:6050/move_mouse -u <username>:<password>
```

#### /jumpscare *(GET)*
Crea un frame fullscreen con un'immagine jumpscare e in sottofondo
viene riprodotto un'audio di un'urlo.
```shell
curl -X GET -i http://<ip>:6050/jumpscare -u <username>:<password>
```

#### /blue_screen/{time} *(GET)*
Crea un frame fullscreen che visualizza il blue_screen di windows per il tempo 
specificato in {time:milliseconds}, il path {time} è facoltativo, se omesso verrà visualizzato
per 5 secondi.
```shell
curl -X GET -i http://<ip>:6050/blue_screen/10000 -d -u <username>:<password>
```

#### /notification *(POST)*
Crea delle notifiche di windows.<br/>
**NotificationModel:**
```json
{
  "title": "Titolo della notifica",
  "description": "Descrizione della notifica",
  "tooltip": "Tooltip della notifica",
  "type": "ERROR"
}
```
I valori possibili del campo type sono: ERROR,WARNING,INFO,NONE.
```shell
curl -X POST -i http://<ip>:6050/notification -d <json> -u <username>:<password>
```
