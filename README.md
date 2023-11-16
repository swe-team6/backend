# team6 check

## Setup (not working):

1. Create `settings.xml` file in `{home folder}\.m2\`:

   ```
       <settings>
           <servers>
               <server>
                   <id>ssh-repository</id>
                   <username>username</username>
                   <privateKey>C:\Users\User\.ssh\SWE\username</privateKey>
                   <configuration>
                       <sshExecutable>plink</sshExecutable>
                       <scpExecutable>pscp</scpExecutable>
                       <!-- <sshArgs>other arguments you may need</sshArgs> -->
                   </configuration>
                   <filePermissions>664</filePermissions>
                   <directoryPermissions>775</directoryPermissions>
               </server>
           </servers>
       </settings>
   ```

2. Set up SSH key access to the server with corresponding username (generate on local comp and copy .pub over to the server)

3. install `plink` and run `plink 35.207.161.15` and reply `y`.

## Setup 2:

1. Set up github deploy keys on server

2. Install tomcat

3. Install [JDK 17](https://www.rosehosting.com/blog/how-to-install-java-17-lts-on-ubuntu-20-04/)

4. Install [maven 3.9.5](https://phoenixnap.com/kb/install-maven-on-ubuntu)

5. Clone github repo using SSH

## Deploy:

1. `mvn clean package -Pproduction`

<!-- 2. Copy .war file to the server path `/opt/tomcat/updated/webapps/` -->
