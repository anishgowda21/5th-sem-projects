# NS3 Programs useing C++

## Steps to run the Programs

- Download the latest ns3 tar file from https://www.nsnam.org/releases/
- Extract the tar file using the command

```bash
tar xvfj ns-allinone-x.xx.tar.bz2
```
- cd into the extracted file and run 

(make sure to have a c and c++ compiler and python interpreter pre installed)
```bash
./build.py --enable-examples --enable-tests
```

- After the build is complete cd into ns-x-xx

- Place the program files in the ```scratch``` folder

- Go back to the ns-x-xx folder

- Compile and run the program using

```bash
./waf ---run scratch/filename
```