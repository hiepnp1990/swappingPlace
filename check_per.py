import axmlparserpy.apk as apk
import os
import shutil

for f in os.listdir(r"C:\Users\root\Desktop\New folder\apks\apks"):
	#print f
	fullpath = os.path.join(r"C:\Users\root\Desktop\New folder\apks\apks", f)
	ap = apk.APK(fullpath)
	#print ap.get_package()
	#print ap.get_androidversion_name()
	perms = ap.get_permissions()
	sms = False
	internet = False
	for perm in perms:
		#if "SEND_SMS" in perm:
		#	sms = True
		if "INTERNET" in perm:
			internet = True
	#if sms and internet:
	if internet:
	#if sms:
		classes = False
		files = ap.get_files()
		for fi in files:
			if "lib" in fi:
				print "=================================================="
				print f
				print fi
				print perms
				newname = os.path.join(r"C:\Users\root\Desktop\New folder\apks\tmp", f)
				shutil.copyfile(fullpath, newname)
		#break
exit(0)
