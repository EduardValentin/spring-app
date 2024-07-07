drop function if exists public.get_awarded_transactions;

CREATE FUNCTION get_awarded_transactions() RETURNS refcursor AS '
    DECLARE
        ref refcursor;
    BEGIN
        OPEN ref FOR select iso, participants.ftrparticipant, participantshortname, tradetype,
		peaktype, auctiontype, hedgetype,  sourceid, sinkid, counterflow, contractsize,
		cost, revenue, profit, hours, hrs_in_period
		from trans
		inner join participants on trans.ftrparticipant = participants.ftrparticipant;

        RETURN ref;
    END;
'
LANGUAGE plpgsql;