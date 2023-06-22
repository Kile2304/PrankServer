$webServer = "localhost"
$pathToJar = "WebServer.jar"

$portList=@(6050, 6040, 6060, 5050, 5040, 5060)
while (!(Test-Path -Path .shutdown -PathType Leaf)) {
    $found=0

    Foreach($port in $portList) {
        try {
            # Write-Host $port
            $tcpClient = New-Object System.Net.Sockets.TcpClient
            $tcpClient.Connect($webServer, $port)
            if ($tcpClient.Connected) {
                Write-Host "Found"
                $found=1
                break
            }
        } catch { }
        finally {
            $tcpClient.Close()
        }
    }
    
    if ($found -eq 0) {
        # Write-Host "Running command"
        Start-Process javaw -ArgumentList "-jar", $pathToJar
        # Write-Host "Executed"
    }

    Start-Sleep -Seconds 300  # Tempo di attesa tra ogni iterazione del loop
}

if (Test-Path -Path .shutdown -PathType Leaf) {
    Remove-Item -Path .shutdown
}
