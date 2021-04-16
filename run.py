'''
Usage : <main> [args]
args:
  <none> : compile and run
  clean  : clean the out directory

example:
`python run.py`
`python run.py clean`  
'''



import os
import sys

from os.path import join

current_dir = os.getcwd()
src = "src"
main = join(src, "Main.java")

def compile():
    print("Currently not working")
    print("Try:")
    print("1. cd src")
    print("2. javac Main.java && java Main")
    return
    command = "javac " + main

    print(command)
    os.system(command)
    print("compiled")

    os.system("java " + main)

def clean():
    for file in os.listdir(src):
        if file.endswith(".class"):
            os.remove(join(src, file))

if __name__ == "__main__":
    if len(sys.argv) == 1:
        compile()
    elif len(sys.argv) == 2 and sys.argv[1] == "clean":
        clean()
    elif sys.argv[1] == "help":
        print("Usage : <main> [args]")
        print("args:")
        print("  <none> : compile and run")
        print("  clean  : clean the out directory")
    else:
        print("Usage : <main> [args]")
        print("args:")
        print("  <none> : compile and run")
        print("  clean  : clean the out directory")