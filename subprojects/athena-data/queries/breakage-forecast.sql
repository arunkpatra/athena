-- Breakage forecast for this year (YTD)

-- NOTE: The full algorithm is implemented in code. See org.athena.api.services.AthenaBackendServiceImpl#getBreakageForecastForCard
-- What was the breakage an an year ago
-- cols: card_type_code, total_breakage_previous, value_of_cards_with_breakage_previous

-- What is volume this year
-- cols: card_type_code, value_of_non_expired_cards_ytd

-- Breakage Forecast
-- cols: card_type_code, breakage_forecast
--      = (total_breakage_previous * value_of_cards_with_breakage_current)/value_of_cards_with_breakage_previous

select *
from historical_breakage_rate;