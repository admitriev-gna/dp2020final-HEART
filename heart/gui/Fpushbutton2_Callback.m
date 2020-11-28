function Fpushbutton1_Callback(handles)

global countpushbutton;
global pation;
countpushbutton = 0;

% массив вопросов
set(handles.text3,'string','”кажите возраст:');
set(handles.edit2,'string','');
set(handles.edit1,'string','54-101-001-01');
set(handles.text11,'String',[]);

set(handles.text5,'string',1);
set(handles.uipanel4,'BackgroundColor',[0.95 0.95 0.95]);
set(handles.text10,'BackgroundColor',[0.95 0.95 0.95]);
set(handles.text10,'String',[]);  

load heart_cut.mat;
ID = tbcell{1}(1, 1);
set(handles.edit1,'String',string(ID.Variables));
pation = 9;

