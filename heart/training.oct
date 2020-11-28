function training(data,class_name)



%% Делим базу на 2
ranrows = randperm(size(data,1));
k = 0.80;
data_train = data(ranrows(1:round(k*height(data))), :);
data_recogn = data(ranrows(round(k*height(data)+1):end), :);
class_name_train = class_name(ranrows(1:round(k*height(data))));
class_name_real = class_name(ranrows(round(k*height(data)+1):end));


%% Обучение
tree = fitctree(data_train,class_name_train); %,'OptimizeHyperparameters','auto'
save('tree.mat','tree');

%% распознавание
%% Дерево
class_name_recogn = predict(tree, data_recogn);

class_name_real = class_name_real~=0;
class_name_recogn = class_name_recogn~=0;
P = mean(class_name_real == class_name_recogn);
disp('Одиночное дерево')
disp(['Средняя вероятность ' num2str(P)]);
MP(1,1) = mean(class_name_recogn(class_name_real==0)==0);
MP(1,2) = mean(class_name_recogn(class_name_real==0)==1);
MP(2,1) = mean(class_name_recogn(class_name_real==1)==0);
MP(2,2) = mean(class_name_recogn(class_name_real==1)==1);
disp('Нет риска   Есть риск');
disp(MP);

view(tree,'Mode','graph');


imp = predictorImportance(tree);

figure;
[imp, IX] = sort(imp,'descend');
tick = tree.PredictorNames;
tick = tick(IX);
bar(imp);
title('Predictor Importance Estimates');
ylabel('Estimates');
xlabel('Predictors');
h = gca;
h.XTickLabel = tick;
h.XTickLabelRotation = 90;
h.TickLabelInterpreter = 'none';
grid on; xlim([1,10])


%% Случайный лес
Mtree = TreeBagger(100,data_train,class_name_train,'OOBPrediction', 'on', 'Method', 'classification','NumPredictorsToSample', 'all'); %regression

save('Mtree.mat','Mtree');
figure;
oobErrorBaggedEnsemble = oobError(Mtree);
plot(oobErrorBaggedEnsemble)
xlabel 'Number of grown trees';
ylabel 'Out-of-bag classification error';

class_name_recogn=[];
class_name_recogn_cell = predict(Mtree, data_recogn);
for n=1:length(class_name_recogn_cell)
    class_name_recogn(n,1) = str2double(class_name_recogn_cell{n});
end
class_name_real = class_name_real~=0;
class_name_recogn = class_name_recogn~=0;
P = mean(class_name_real == class_name_recogn);
disp('Случайный лес')
disp(['Средняя вероятность ' num2str(P)]);
MP(1,1) = mean(class_name_recogn(class_name_real==0)==0);
MP(1,2) = mean(class_name_recogn(class_name_real==0)==1);
MP(2,1) = mean(class_name_recogn(class_name_real==1)==0);
MP(2,2) = mean(class_name_recogn(class_name_real==1)==1);
disp('Нет риска   Есть риск');
disp(MP);


%% Ансамбль деревьев
t = templateTree('Reproducible',true);
Mtree = fitcensemble(data_train,class_name_train,'Method','AdaBoostM2','Learners',t);%,'OptimizeHyperparameters','auto'

% save('Mtree.mat','Mtree');
% figure;
% oobErrorBaggedEnsemble = oobError(Mtree);
% plot(oobErrorBaggedEnsemble)
% xlabel 'Number of grown trees';
% ylabel 'Out-of-bag classification error';

class_name_recogn=[];
class_name_recogn_cell = predict(Mtree, data_recogn);
for n=1:length(class_name_recogn_cell)
    class_name_recogn(n,1) = class_name_recogn_cell(n);
end
class_name_real = class_name_real~=0;
class_name_recogn = class_name_recogn~=0;
P = mean(class_name_real == class_name_recogn);
disp('Ансамбль деревьев')
disp(['Средняя вероятность ' num2str(P)]);
MP(1,1) = mean(class_name_recogn(class_name_real==0)==0);
MP(1,2) = mean(class_name_recogn(class_name_real==0)==1);
MP(2,1) = mean(class_name_recogn(class_name_real==1)==0);
MP(2,2) = mean(class_name_recogn(class_name_real==1)==1);
disp('Нет риска   Есть риск');
disp(MP);


%% Вычисление доверительых интервалов
find_different()
% mean_sign = zeros([1,size(data,2)]);
% std_sign = zeros([1,size(data,2)]);
% for n=1:size(data,2)
%     if isnumeric(table2array(data(:,n)))
%         mean_sign(n) = mean(table2array(data(:,n)));
%         std_sign(n) = std(table2array(data(:,n)));
%     end
% end
% 
% trusted_intervals = [mean_sign-3*std_sign;mean_sign+3*std_sign];
% save('trusted_intervals.mat','trusted_intervals')


