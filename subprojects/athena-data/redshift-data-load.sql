copy card_type from 's3://arunkpatra-athena/card-type-data.txt'
    credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' region 'ap-south-1';

copy merchant from 's3://arunkpatra-athena/merchant-data.txt'
    credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' region 'ap-south-1';

copy card from 's3://arunkpatra-athena/card-data.txt'
    credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' dateformat 'YYYY-MM-DD' region 'ap-south-1';

copy customer from 's3://arunkpatra-athena/customer-data.txt'
    credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' dateformat 'YYYY-MM-DD' region 'ap-south-1';

copy transaction from 's3://arunkpatra-athena/transaction-data.txt'
    credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' dateformat 'YYYY-MM-DD' region 'ap-south-1';
