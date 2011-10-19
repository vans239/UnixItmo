#!/bin/bash
function isInit {
	if [ -d .$1 ] ; then
		return 1 
	fi
	return 0
}
 
function init {
	isInit $1
	now=$?
	[ $now -eq 1 ] && echo "File have been already initiated" && exit

	mkdir .$1
	cp $1 .$1/last
	echo 0 >> .$1/version 
	echo File $1 was initiated
}
function status {
	isInit $1
	now=$?
	[ $now -ne 1 ] && echo "File haven't been initiated" && exit
	
	echo Checking status
	if diff -u $1 .$1/last > /dev/null; then
		echo Same files	
	else
		echo Different files	
	fi
}
function diffFiles {
	isInit $1
	now=$?
	[ $now -ne 1 ] && echo "File haven't been initiated" && exit
	diff -u $1 .$1/last
}
function commit {
	isInit $1
	now=$?
	[ $now -ne 1 ] && echo "File haven't been initiated" && exit
	
	getVersion $1
	now=$?
	diff -u .$1/last $1 > .$1/$now.diff
	cp $1 .$1/last
	echo File $1 commited with revision $(( $now + 1))	
	incVersion $1	
}
function update {
	isInit $1
	now=$?
	[ $now -ne 1 ] && echo "File haven't been initiated" && exit
	
	cp .$1/last $1
	getVersion $1	
	counter=$?
	while [ $counter -gt $2 ] ; do
		let counter-=1
		patch -R -u $1 .$1/$counter.diff > /dev/null	
	done
	commit $1 
}

function getVersion {
	file=.$1/version
	read var < $file	
	return $var
}
function incVersion {
	file=.$1/version 
	getVersion $1
	counter=$(($? + 1))	
	echo $counter > $file 
}
if [ $# -lt 2 ] ; then
	echo Where is my parameters
	echo init
	echo commit
	echo status
	echo diff
	echo update
	exit
fi
if [ $1 = "init" ] ; then
	init $2
	exit
fi
if [ $1 = "commit" ] ; then
	commit $2
	exit
fi
if [ $1 = "status" ] ; then
	status $2
	exit
fi
if [ $1 = "diff" ] ; then
	diffFiles $2
	exit
fi
if [ $1 = "update" ] ; then
	update $2 $3
	exit
fi

