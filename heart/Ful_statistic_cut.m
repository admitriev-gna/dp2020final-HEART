cl;

load('heart_cut.mat');


P = size(tbcell,1); % Число страниц
N = size(tbcell{1},1); % Число строк

data = tbcell{1};
data = data(:,2:end);
data = fillmissing(data,'constant',0,'DataVariables',@isnumeric);

k = 92; % Индекс Артериальной гипертензии
% class_name = table2array(data(:,k)) | table2array(data(:,k+1)) | table2array(data(:,k+2)) | table2array(data(:,k+3)) | table2array(data(:,k+4)); % Читаем столбцы с заболеванием
% data = data(:,1:k-1); % Вырезаем столбцы с заболеванием

% figure; histogram(table2array(data(:,k)) + table2array(data(:,k+1)) + table2array(data(:,k+2)) + table2array(data(:,k+3)) + table2array(data(:,k+4))); grid on
class_name = table2array(data(:,k)) + 10*table2array(data(:,k+1)) + 100*table2array(data(:,k+2)) + 1000*table2array(data(:,k+3)) + 10000*table2array(data(:,k+4)); % Читаем столбцы с заболеванием
data = data(:,1:k-1); % Вырезаем столбцы с заболеванием


training(data,class_name);
