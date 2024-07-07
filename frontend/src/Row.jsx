import React from 'react';
import { formatCurrency } from './util/FormatUtil.js';
export const Row = ({ item }) => (
	<div className="row">
		<div id="participant" className="column" title={item.participantfullname}>
			{item.participantshortname}
		</div>
		<div id="tradetype" className="column">
			{item.tradetype}
		</div>
		<div id="peaktype" className="column">
			{item.peaktype}
		</div>
		<div id="hedgetype" className="column">
			{item.hedgetype}
		</div>
		<div id="source" className="column">
			{item.sourceid}
		</div>
		<div id="sink" className="column">
			{item.sinkid}
		</div>
		<div id="size" className="column">
			{item.contractsize}
		</div>
		<div id="cost" className="column">
			{formatCurrency(item.cost)}
		</div>
		<div id="revenue" className="column">
			{formatCurrency(item.revenue)}
		</div>
		<div id="profit" className="column">
			{formatCurrency(item.profit)}
		</div>
	</div>
);

export const Header = ({ item }) => (
	<div className="row header">
		<div id="participant" className="column">
			Name
		</div>
		<div id="tradetype" className="column">
			Trade Type
		</div>
		<div id="peaktype" className="column">
			Peak Type
		</div>
		<div id="hedgetype" className="column">
			Hedge Type
		</div>
		<div id="source" className="column">
			Source
		</div>
		<div id="sink" className="column">
			Sink
		</div>
		<div id="size" className="column">
			MW
		</div>
		<div id="cost" className="column">
			Cost
		</div>
		<div id="revenue" className="column">
			Revenue
		</div>
		<div id="profit" className="column">
			Profit
		</div>
	</div>
);
