
#!/bin/bash
archive=tex2html-test.tar.7z
if [ -n "$1" ] && [[ "$1" =~ .*tar.7z ]] && [ -f "$1" ] ; then
	archive=$1
fi 
# 
#"tex2html-test.tar.7z"
passwd=4240
found=0
while [ $passwd -ne 10000 ] && [ $found -eq 0 ] ; do
	let passwd=$passwd+1
	7z x -y -p`printf "%04d" $passwd` $archive >> /dev/null
	if [ $? -eq 0 ] ; then
		found=1
	fi
done
if [ $found -ne 1 ] ; then
	exit 1;
fi
archive="tex2html-test.tar"
tar xvf $archive >> /dev/null
exit 0

