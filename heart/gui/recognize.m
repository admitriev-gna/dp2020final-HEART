function [P, Pint, untrusted_index, untrusted_name] = recognize(signs)

%% распознавание по лесу
load('Mtree.mat','Mtree');
load('trusted_intervals.mat','trusted_intervals');

N = Mtree.NumTrees;

for n=1:N
    tmp = predict(Mtree.Trees{n}, signs);
    class_all(n) = str2double(tmp{1});
end

Pint = mean(class_all>0);

Mclass = zeros(1,5);
for n=1:N
    for i=1:5
        if mod(class_all(n),2)
            Mclass(i) = Mclass(i)+1;
        end
        class_all(n) = floor(class_all(n)/10);
    end
end
P = Mclass/N;

%% Найдем аномальные даные
untrusted_index = [];
for  n=1:size(signs,2)
    val = table2array(signs(1,n));      
    if isnumeric(val) && sum(abs(trusted_intervals(n)))~=0
        if val<trusted_intervals(1,n) || val>trusted_intervals(2,n)
            untrusted_index = [untrusted_index,n];
        end
    end
end
untrusted_name = signs.Properties.VariableNames(untrusted_index);
