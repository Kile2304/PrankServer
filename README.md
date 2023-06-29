# PrankServer
This WebServer tries to connect to one of the following ports:
- 6050
- 6040
- 6060
- 5050
- 5040
- 5060

If none of these ports is available, the application does not start.

## Endpoint

### Autenticati

#### /version *(GET)*
Returns the current version number of the application.
```shell
curl -X GET -i http://<ip>:6050/version -u <username>:<password>
```

#### /ping *(GET)*
Simple test to check if the server is up.
```shell
curl -X GET -i http://<ip>:6050/ping -u <username>:<password>
```

#### /shutdown *(GET)*
Shuts down the server.
```shell
curl -X GET -i http://<ip>:6050/shutdown -u <username>:<password>
```

#### /browse *(POST)*
Allows the opening of the browser to the URL passed as a parameter.
The url must be passed in the body.
```shell
curl -X POST -i http://<ip>:6050/browse -d <url> -u <username>:<password>
```

#### /popup *(POST)*
Allows the display of a custom popup that always stays on top. 
The body that should be passed is the following
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
Causes the mouse to move in 5 different positions with a 3 to 6 second
delay after each movement.
```shell
curl -X GET -i http://<ip>:6050/move_mouse -u <username>:<password>
```

#### /jumpscare *(GET)*
Creates a fullscreen frame with a jumpscare image and a scream audio 
is played in the background.
```shell
curl -X GET -i http://<ip>:6050/jumpscare -u <username>:<password>
```

#### /fart *(GET)*
Plays the sound of a fart.
```shell
curl -X GET -i http://<ip>:6050/fart -u <username>:<password>
```

#### /invisible_frame/{time} *(GET)*
Crea un frame invisibile renderizzando il computer inutilizzabile per un certo periodo di tempo.
time in {time:milliseconds}, the {time} path is optional, if omitted it will
be displayed for 10 seconds.
```shell
curl -X GET -i http://<ip>:6050/invisible_frame -u <username>:<password>
```

#### /random_typer/{time} *(POST)*
Types random keys for a specified period of time.
time in {time:milliseconds}, the {time} path is optional, if omitted it will
be executed for 10 seconds.
```shell
curl -X POST -i http://<ip>:6050/random_typer -d '{"time":<time_in_milliseconds>, "keys":"<keys_to_type>"}' -u <username>:<password>
```

#### /screen_flip/{time} *(GET)*
Takes a screenshot, flips it and displays it in fullscreen to simulate a flipped display effect.
time in {time:milliseconds}, the {time} path is optional, if omitted it will
be displayed for 10 seconds.
```shell
curl -X GET -i http://<ip>:6050/screen_flip -u <username>:<password>
```

#### /blue_screen/{time} *(GET)*
Creates a fullscreen frame displaying the windows blue_screen for the specified 
time in {time:milliseconds}, the {time} path is optional, if omitted it will 
be displayed for 5 seconds.
```shell
curl -X GET -i http://<ip>:6050/blue_screen/10000 -d -u <username>:<password>
```

#### /notification *(POST)*
Creates Windows notifications.
**NotificationModel:**
```json
{
  "title": "Titolo della notifica",
  "description": "Descrizione della notifica",
  "tooltip": "Tooltip della notifica",
  "type": "ERROR"
}
```
The possible values of the type field are: ERROR, WARNING, INFO, NONE.
```shell
curl -X POST -i http://<ip>:6050/notification -d <json> -u <username>:<password>
```
