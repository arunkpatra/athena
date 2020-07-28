-- YTD Top merchants with breakage
-- Description: Find out the total breakage by merchant

-- Verified: OK

-- Approach
-- 1. Which cards have expired as of today in the last one year?
-- 2. What was the breakage on each such card?
-- 3. Group by card type and sum ALL breakages. That gives the total breakage for a card type


select merchant_name, AMB.merchant_breakage, CURRENT_DATE as as_of_date
from (select merchant_code, sum(breakage) as merchant_breakage
      from (select P.merchant_code                   as merchant_code,
                   P.tx_value                        as purchase_value,
                   R.total_redeemed                  as redeemed_value,
                   (purchase_value - redeemed_value) as breakage
            from (select gc_uuid, merchant_code, tx_value
                  from transaction
                  where tx_type = 'PURCHASE') P,                        -- Purchase value on card instance
                 (select gc_uuid, sum(tx_value) as total_redeemed
                  from transaction
                  where tx_type = 'REDEMPTION'
                  group by gc_uuid) R,                                  -- Redeemed value on card instance
                 (select gc_uuid
                  from card
                  where gc_expiry_date < current_date
                    and (CURRENT_DATE::date - gc_expiry_date) < 365) E, -- Expired card instances in this year (YTD)
                 card                                                   -- card instances
            where P.gc_uuid = E.gc_uuid
              and E.gc_uuid = card.gc_uuid
              and E.gc_uuid = R.gc_uuid) MB
      group by merchant_code) AMB,
     merchant
where AMB.merchant_code = merchant.merchant_code
order by AMB.merchant_breakage desc;


