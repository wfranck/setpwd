setpwd
======

Guarda/recupera pares clave/valor encriptado en archivo.

A simple routine class (using an encryption library, deployed in a jar file) is provided to ease the tasks of reading and writing password pairs.

You can run it having the included jar in your classpath.

Add or modify a password (if the file does not exist it is created, else the new pair will be appended).
java setpwd -a /home/franck/test.pwd 1234567890 nomConf mysecretpassword


Remove a pair
java setpwd -d /home/franck/test.pwd 1234567890 nomConf


Read a password
java setpwd -r /home/franck/test.pwd 1234567890 nomConf


List (decrypted) pairs in the file
java setpwd -l /home/franck/test.pwd 1234567890


The current version of the tool does not allow to use spaces in any parameter.


The method set PwdStore contains a single line of code that instructs the pwdStore routines to use a specific password file and an encryption key.

obj.setPwdStore("/home/franz/test.pwd", "1234567890");

Once the password Storage parameters are set, passwords can be retrieved using the method obj.getPwd(alias) where the "alias" is a convenient name we used to identify a specific password.