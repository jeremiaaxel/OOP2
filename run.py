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
out = "out"
main = "Main.java"

battle_dir = join(src, "Battle")
breeding_dir = join(src, "Breeding")
engimon_dir = join(src, "Engimon")
game_dir = join(src, "Game")
map_dir = join(src, "Map")
player_dir = join(src, "Player")

def compile():
    files = []
    # files.extend(join(battle_dir, filename) for filename in os.listdir(battle_dir))
    # files.extend(join(breeding_dir, filename) for filename in os.listdir(breeding_dir))
    # files.extend(join(engimon_dir, filename) for filename in os.listdir(engimon_dir))
    # files.extend(join(game_dir, filename) for filename in os.listdir(game_dir))
    files.extend(join(map_dir, filename) for filename in os.listdir(map_dir))
    # files.extend(join(player_dir, filename) for filename in os.listdir(player_dir))

    for fil in files:
        if fil.endswith(".class"):
            files.remove(fil)

    command = ""
    for fil in files:
        command += fil + " "

    command = "javac -d " + out + " " + command

    os.system(command)
    print("compiled")

    os.system("cd " + out + " && java " + "Map")

def clean():
    for file in os.listdir(out):
        os.remove(join(out, file))

if __name__ == "__main__":
    if len(sys.argv) == 1:
        compile()
    elif len(sys.argv) == 2 and sys.argv[1] == "clean":
        clean()
    else:
        print("Usage : <main> [args]")
        print("args:")
        print("  <none> : compile and run")
        print("  clean  : clean the out directory")