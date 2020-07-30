# Athena Architecture

This document describes a possible architecture that could be envisaged for a GC breakage forecast platform. It necessarily
does not depict the software implemented by this repository currently. 

The purpose of this technical note is to discuss various aspects that should be considered while designing a scalable 
data warehouse solution to process large amounts of gift card transaction log data to forecast breakages.

## Architecture (30K feet view)

<img src="assets/athena-architecture.png" width="1280px" alt="Athena Architecture" />

The above illustration depicts various architecturally significant components of the solution. Other implementation level
cross-cutting concerns like security, logging, monitoring, audit etc. are not shown for sake of brevity.

Multiple in-house OLTP and possibly external systems (vendors, partners etc.) would continuously deposit large amounts 
of gift card transaction log data (and other card related data like merchant, customer, gift card metadata etc.) into 
an exa-byte scale object storage(Amazon S3) that is cheap.

Client applications would need to access this data and derive insights. These insights would need to be based on analytics
performed on massive amounts of data and possibly stored as materialized views in the DW. The queries can both be very complex
and also may have a need to look at exa-byte scale data over time. While Amazon Redshift by itself can provide complex
query processing at scale, it is beneficial to use Amazon Spectrum (which is serverless) to leverage massive data processing
capability directly looking at S3 data. Amazon Redshift smartly will shift specific tasks that are best handled by Spectrum
under the covers.

Business applications would query Redshift using the Amazon Redshift JDBC or ODBC drivers. Such business applications
can float up REST APIs so that systems of engagement like UI apps can render interactive visualizations.

Note that, the above architecture only depicts the DW part of the landscape. If it is desired to do breakage forecasting
based on machine learning models, other components would participate in the solution. Such components would include 
infrastructure for model training, continuous learning pipelines and model inference serving. In that case, business apps
would ask the model inference serving component to calculate breakage forecasts.

## Architectural Considerations
1. The amount of data in S3 is potentially massive and is expected to grow fairly linearly.
2. Data that exists today in disparate systems probably would need to be loaded into S3.
3. The queries that need to happen on the data are potentially complex and it may not suffice or might become too 
expensive to process at Redshift. 
4. The requirements on the business app are not too stringent as regards scale since the consumers of this analytical
insight might be a few. 
5. Historical breakage rate crunching will likely be quarterly or annual jobs(or some other duration) if one considers
breakage forecast purely based on historical trends. The approach when it is desired to leverage machine learning
models to predict breakage would be necessarily different.
6. Complex and expensive insights are best preserved appropriately in suitable views of the data.

## Technology Options
1. For an AWS heavy shop, with considerable investment in existing AWS technology, it would make sense to adopt AWS technologies
to implement a DW solution and query processing at scale. 
2. The core DW option is Redshift. 
3. Spectrum will be able to scale out to massive amounts of data and bring in cost benefits by levering its serverless aspects. 
4. S3 would be a cheap solution for storage. 
5. The API layer could be built using standard Spring Boot stack. Visualization may be done using custom SPA built
on ReactJS technology. However, this is not necessary of one wishes to just visualize insights directly - in that case
Amazon Athena can be used as a serverless query service.
