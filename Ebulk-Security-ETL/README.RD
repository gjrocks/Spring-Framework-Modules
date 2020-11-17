For developers

i) Please use etl.bat (or security_upgrade.sh if you are using Linux box for development) for upgrading.

    Example:  i)if you dont want to change the password and just to re-encryption 
            
               etl.bat <location of the config folder for a deployment>
               
               e.g. etl.bat C:\Apps\capita-apps-config\ebulk\sites\cheqs
          
               ii) if you want to change password and re-encryption
              etl.bat <location of the config folder for a deployment>  <location of folder for updated properties file> <password>
             e.g.
            etl.bat C:/Apps/capita-apps-config/ebulk/sites/cheqs  C:/Apps/capita-apps-config/ebulk/sites/cheqs/cheqs_generated  Academyy!                


             Make sure for running the code you are using the JDK which has unlimited policy configured.
   
ii) Before upgrading, make sure you have upgraded your DB to 12.3, you can use upgrade sql from this project at resources/etl/upgrade.sql

iii) From STS , you can run the program com.gj.Test.
     It takes 1 or 3 parameters. If you are providing second parameter then you have to provide third.

     a) first parameter : folder location where ebulk configuration. e.g. C:\Apps\capita-apps-config\ebulk\sites\cheqs
     b) second parameter : location of folder for updated properties file
     c) third parameter : new password if there is any
     
     
iv) etl-config.properties : it contains the details of the source and target algorithms to be used.

     