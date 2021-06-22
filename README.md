# Flight Android App
 <img width="196" alt="‏‏לכידה2" src="https://user-images.githubusercontent.com/54501031/122980162-06127380-d3a1-11eb-8997-e9ae5ba59296.PNG" width="650" height="400"></br>

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
 * Enjoy your flight!</br>

## Implementation
This App based on MVVM software architectural pattern that has three main parts that run it, each part with its own designated responsibilities. </br>
The Model (FGPlayer class) interacts with FlightGear via TCP connection, continuously send commands to the flight gear in another thread (decoupling between the invoker thread and the executor thread)
Next, the ViewModel awares to changes in elements from the view when the user inserts data, touces the screen, etc and then notifies the model about these changes by sending them as commands to flight gear that the model will use. </br>
The View is responsible to the visibility of the app and notifies the viewmodel about changes of data of many elements in the app.</br>
 
 ### [UML]
<img src="">

## Collaborators
Developed by Nimrod Gabbay and Maor Malekan.</br>

## Explanation video
