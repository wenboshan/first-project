@echo off  
title Maven�Զ�����ű���������  
color 02  
set project=%~n0  
set curdir=%~dp0  
set partition=%curdir:~0,1%  
cd /d %~dp0  
  
REM ���ļ��豣��ΪANSI���룬������Windows��ִ��  
REM java -cp .;jacob.jar test.CameraTest  
REM rem------------��ʾ��ע�ͣ��൱��Java�е�//  
REM @echo off------��ʾ�رջ��ԣ�������ʾ���м������������(Ĭ��DOS��̻��������ʾ����)  
REM color----------����Ϊ02��ʾDOS���ڱ���Ϊ��ɫ��ǰ��(������)Ϊ��ɫ  
REM xcopy----------�������/Y��ʾ�Զ�����ͬ���ļ�(�������DOS����ѯ��Y����N)  
REM del------------ɾ���ļ����/S����ɾ��Ŀ¼��(��ɾ��Ŀ¼��Ŀ¼�µ�������Ŀ¼���ļ�)��/Q��ʾȷ��Ҫɾ��(����DOS����ʾ�û��Ƿ�ȷ��ɾ��)  
REM rd-------------ɾ���ļ������/S��/Q������del����ĺ�����ͬ  
REM ren------------�������ļ����÷���[ren 11.exe 22.exe]  
REM echo ���������ļ���Ϊ��%project%  
REM echo ���������ļ�����·��Ϊ��%curdir%  
REM echo ���������ļ������̷�Ϊ��%partition%  
REM echo ���������ļ����ڹ���Ϊ��%curdir:~0,30%  
  
echo ��ʼ���Maven���� =================================  
echo "��ӭ�����ǳ�BAT!"%~dp0
xcopy %~dp0\src\main\filters\product\ttjdbc6.jar %~dp0\src\main\webapp\WEB-INF\lib\ /Y
REM call mvn clean package  
call mvn install -DskipTests -P  product 
echo Maven���̴����� =================================     
  
  
echo;  
pause 