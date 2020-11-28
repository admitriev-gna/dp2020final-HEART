function Fpushbutton1_Callback(handles)

global countpushbutton;
global pation;
if countpushbutton == 1
    countpushbutton = countpushbutton+86;
else
    countpushbutton = countpushbutton+1;
end

if countpushbutton > 89
%     chance = 80; %rand(1)*100;
%     if chance > 75
%         set(handles.uipanel4,'BackgroundColor',[1 0 0]);
%     end
% end 
    load heart_cut.mat;
    Tbl = tbcell{1}(pation, [2:92]);
    signs = fillmissing(Tbl, 'constant', 0, 'DataVariables', @isnumeric);
    [P, Pint, ~, untrusted_name] = recognize(signs);
    
    deseses{1} = 'Артериальная гипертензия:';
    deseses{2} = 'ОНМК:';
    deseses{3} = 'Стенокардия, ИБС, инфаркт миокарда:';
    deseses{4} = 'Сердечная недостаточность:';
    deseses{5} = 'Прочие заболевания сердца:';
    
    str = {};
    if (sum(P<0.7) == 5)
        strIP = ['Предрасположенность к ССЗ: ', num2str(Pint*100), '%'];
        set(handles.text10,'String',strIP);   
    else
        for i = 1:5
                set(handles.uipanel4,'BackgroundColor',[1 0 0]);
                set(handles.text10,'BackgroundColor',[1 0 0]);
                str = [str; strcat(deseses{i}, ' ', num2str(100*P(i)), '%')];
        end
        set(handles.text10,'String',str);   
    end
    
    str = {};
    if ~isempty(untrusted_name)
        for n=1:length(untrusted_name)
            str = [str; untrusted_name{n}];
        end
        set(handles.text11,'String',str);
    end
    
else

    % массив вопросов
    quest = FSetQuestions;

    var(countpushbutton) = str2double(get(handles.edit2,'string'));
    set(handles.text3,'string',quest(countpushbutton));
    set(handles.edit2,'string','');

    countQues = countpushbutton+1;
    set(handles.text5,'string',countQues);
end
