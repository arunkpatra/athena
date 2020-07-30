copy card_type from 's3://arunkpatra-athena/card-type-data-v2.csv' credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' region 'ap-south-1' ignoreheader 1;

copy merchant from 's3://arunkpatra-athena/merchant-data-v2.csv' credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' region 'ap-south-1' ignoreheader 1;

copy card from 's3://arunkpatra-athena/card-data-v2.csv' credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' dateformat 'YYYY-MM-DD' region 'ap-south-1' ignoreheader 1;

copy customer from 's3://arunkpatra-athena/customer-data-v2.csv' credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' dateformat 'YYYY-MM-DD' region 'ap-south-1' ignoreheader 1;

copy transaction from 's3://arunkpatra-athena/transaction-data-v2.csv' credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' dateformat 'YYYY-MM-DD' region 'ap-south-1' ignoreheader 1;

copy historical_breakage_rate from 's3://arunkpatra-athena/historical-breakage-rate-data-v2.csv' credentials 'aws_iam_role=arn:aws:iam::459024718193:role/myRedshiftRole'
    delimiter ',' dateformat 'YYYY-MM-DD' region 'ap-south-1' ignoreheader 1;
