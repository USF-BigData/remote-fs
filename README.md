# Read-Only Remote FS with FUSE

Here's a small demo project that shows how to mount a remote server as a local client file system using FUSE. In other words, you'll start the server on one side, and then "mount" it on a client machine that can then see its files.

**WARNING**: This is probably best described as a *work in progress* rather than a 100%-reliable amazing piece of enterprise code. So, caveat emptor.

```
mvn clean package

# To run the server:
java -cp ./target/rfs-1.0.jar edu.usfca.cs.rfs.FileServer port root-dir

# To run the client:
java -cp ./target/rfs-1.0.jar: edu.usfca.cs.rfs.ClientFS server port mount-point
```

