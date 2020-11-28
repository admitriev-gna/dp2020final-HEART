cl;

load('heart.mat');


P = size(tbcell,1); % Число страниц
N = size(tbcell{1},1); % Число строк

data = [];
for p=1:P
    tbcell{p} = tbcell{p}(:,2:end); % Удаляем ID
    if p==8
        tbcell{p} = tbcell{p}(:,2:end); % Удаляем дубликат "Профессия"
    end
    data = cat(2,data,tbcell{p});
end

% data = tbcell{2};

bed_index = [2 17 19 21 23 25 47 49 90 91 126 133 361-400 803 804];
good_ind = [];
for n=1:size(data,2)
    if sum(bed_index==n)==0
        good_ind = [good_ind, n];
    end
end

data = data(:,good_ind);

k = 15; % Индекс Артериальной гипертензии
class_name = sum(table2array(data(:,k:k+4)),2)>0;
data = data(:,[1:k-1,k+5:end]); % Вырезаем столбцы с заболеванием
data = fillmissing(data,'constant',0,'DataVariables',@isnumeric);



training(data,class_name);