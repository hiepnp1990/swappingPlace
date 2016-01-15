import axmlparserpy.apk as apk
import os
import shutil

for f in os.listdir(r"C:\Users\root\Desktop\New folder\sony\apks"):
	#print f
	fullpath = os.path.join(r"C:\Users\root\Desktop\New folder\sony\apks", f)
	try:
		ap = apk.APK(fullpath)
		#print ap.get_package()
		#print ap.get_androidversion_name()
		perms = ap.get_permissions()
	except:
		print "====================ERROR========================="
		print f
		continue
	sms = False
	internet = False
	for perm in perms:
		if "SEND_SMS" in perm:
			sms = True
		#if "INTERNET" in perm:
		#	internet = True
	#if sms and internet:
	#if internet:
	if sms:
		print "=================================================="
		print f
		print perms
		newname = os.path.join(r"C:\Users\root\Desktop\New folder\sony\sms", f)
		shutil.copyfile(fullpath, newname)
		#break
exit(0)
