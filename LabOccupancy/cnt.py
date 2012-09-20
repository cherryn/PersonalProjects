#!/usr/bin/python

import json

lookup = { 'ssl':1,'pod':2,'moo':3,'sac':4 }

rooms = [
	
	{ # 0-Windows Lab, cannot ssh.
		'name':'B160',
		'num': 0,
		'max':26
	},
	{ # 1-SSLab
		'name':'B158',
		'num': 0,
		'max':24
	},
	{ # 2-Pod
		'name':'B148',
		'num': 0,
		'max': 26
	},
	{ # 3-Moore
		'name':'B146',
		'num': 0,
		'max': 24
	},
	{ # 4-Sac
		'name':'B131',
		'num': 0,
		'max': 13
	}
];

f = open("../.users.txt")


room=-1

for line in f.readlines():
	line= line.strip()
	if len(line)==0: continue; #do nothing
	elif room==-1: # Not currently processing computer
		if line[:3] in lookup: 
			room = lookup[line[:3]]
			Found=False
	elif line=="--begin users--": continue; #do nothing
	elif line=="--end host--": 
		if Found: rooms[room]['num']+=1
		room=-1 #done with comp
	else: 
		# If user running x-session, BAM. Counted.
		if ' tty' in line: Found=True

f=open("Rooms.jsonp","wb")
f.write("Rooms = '")
f.write(json.dumps(rooms))
f.write("';");
f.close()
