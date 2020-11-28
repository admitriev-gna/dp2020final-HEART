% cl;

load('heart_cut.mat');

data = tbcell{1};
data = data(:,2:end);
data = fillmissing(data,'constant',0,'DataVariables',@isnumeric);

bad_pattern = sum(table2array(data(:,92:96)),2)>0;
good_pattern = sum(table2array(data(:,92:96)),2)==0;


mean_sign = zeros([1,size(data,2)]);
std_sign = zeros([1,size(data,2)]);
for n=1:size(data,2)
    if isnumeric(table2array(data(:,n)))
        tmp_data = table2array(data(:,n));
        bad_data = tmp_data(bad_pattern);
        good_data = tmp_data(good_pattern);
        
        mean_sign(n) = mean(good_data);
        std_sign(n) = std(good_data);
                                              
%         figure(1); histogram(bad_data,'FaceColor','r'); hold on; histogram(good_data,'FaceColor','g'); hold off; grid on;
%         title(data.Properties.VariableNames(n));
%         pause
    end
end

trusted_intervals = [mean_sign-3*std_sign;mean_sign+3*std_sign];

save('trusted_intervals.mat','trusted_intervals')