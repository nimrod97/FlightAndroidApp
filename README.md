# Flight Android App

An android app written in Java and Kotlin (in android studio IDE) that implements a joystick that can control the airplane and fly it.</br>
The app connects via tcp socket connection into the flight gear and after that you can control the throttle and the rudder that appear as sliders in the app, 
and also you can control the elevator and the aileron via the joystick (the x axis is the aileron and the y axis is the elevator).</br>
## Installation and Running
* Open the FlightGear API or download it right [here](https://www.flightgear.org). </br>
* On the settings add to the addional settings this command: </br>
  --telnet=socket,in,10,127.0.0.1,6400,tcp </br>
 This command tells the flight gear simulator to listen at port 6400 and in local host.
 * Download the project to your local computer, then you can run the app on an emulator or just plug your personal phone to the computer and you can run on your phone and also download it!
 (via developer mode). </br>
 * When openning the app, type the ip and the port that the flight gear listens at (the port will be 6400 and the ip will be the ip of your personal computer - you can find it in the terminal by typing ipconfig when using Windows or by typing ifconfig when using Linux or Mac).</br>
 * Enjoy your flight!
 
