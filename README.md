MFA Death
======
MFA Death is the first of a kind when it comes to security research tools on Android, 
only designed for an example of Invisidroid usage scenarios.. it is now
a powerful tool of it's own - deserving of its own repo.

This application was designed strictly for security education only,
DO NOT INSTALL ON UNAUTHORISED DEVICES.

MFA Death is a hidden application that replicates the victim devices MFA,
Multi Factor Authentication codes and sends them to the installer.

![screen](https://imgur.com/a/D7mwaPu)

## Installing
* Install on Victim Android Device
* Open and Set Phone Number of Yourself
( Do not enter the same number as the device or it will stop the message from
sending, this is to combat a infinite loop sending messages )
* Set the Name of the Device to recognise multiple device installs
* Set your Private Launch Code
* Toggle to Activated
* Close the Application and wait for Icon to dissapear from app menu

## Using the Replicated Code
* Open up a browser and go to the target website
* Enter the victims phone number and press Forgotten Password
* Choose the MFA/2FA SMS Code option to recover the account
* Press Send Code
* Enter the Replicated Code you get via the Set Phone
* Immediately remove other devices and change passwords
* Disable 2FA and MFA in account settings to stop restore

This is an example usage of Killing a users 2FA and MFA account
with the Replicate and Send technique.

## History and Development Template
What is Invisidroid ?
Invisidroid has been inspired by hidden applications on Android,    
the concept was to break the difficulty in building your own Hidden     
applications for Android.    

Hidden applications are not launched by the App Launcher but by calling     
a secret number in your dialer.    

Usually ghis is a cumbersome and annoying process with changing API's but     
i have built this to work for Android 9 and still not need the     
permission acceptance for everything.    
 
This application is specifically for Security Research development and     
education, however considering i have released it under MIT license terms     
you are free to modify and distribute it as you wish.    
    
- Kind Regards, Elijah C.   
### Try Invisidroid 
[![demo button](https://i.imgur.com/3Ugm8J7.jpg)](https://github.com/WokeWorld/Invisidroid) 


## Technology
* Android
* Java
* XML

## Installation
* You can choose to test the application by downloading the Test.apk file,    
  otherwise building instructions are below.    
* Launch the application from your secret code.   

## Building Instructions
* Download and Open the project in Android Studio    
* Customise the Project and give it a test       


## Test the Application in a Pre-built APK
[![demo button](https://i.imgur.com/3Ugm8J7.jpg)](https://github.com/WokeWorld/MFA-Death/blob/master/MFADeath.apk?raw=true) 

