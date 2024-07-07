import React from 'react';
import Typography from '@material-ui/core/Typography';
import { Input, TextField, MenuItem, InputLabel, IconButton } from '@material-ui/core';
import Select from '@material-ui/core/Select';
import FirstPageIcon from '@material-ui/icons/FirstPage';
import LastPageIcon from '@material-ui/icons/LastPage';
export const ControlBar = ({ page, pages, setPage, sort, setSort }) => (
	<>
		<IconButton title="First Page" size="small" onClick={() => setPage(1)}>
			<FirstPageIcon />
		</IconButton>
		<TextField
			title="Page Number"
			type="number"
			inputProps={{ min: 1, max: pages }}
			value={page}
			onChange={event => setPage(Number(event.target.value))}
		/>
		<Typography>of {pages}</Typography>
		<IconButton title="Last Page" size="small" onClick={() => setPage(pages)}>
			<LastPageIcon />
		</IconButton>
		<InputLabel htmlFor="sort">Sort&nbsp;&nbsp;</InputLabel>
		<Select
			title="Sort Field"
			value={sort}
			onChange={event => setSort(event.target.value)}
			input={<Input name="sort" id="sort" />}
		>
			<MenuItem value={'contractsize,participantfullname'}>MW</MenuItem>
			<MenuItem value={'tradetype,participantfullname'}>Trade Type</MenuItem>
			<MenuItem value={'cost'}>Cost</MenuItem>
			<MenuItem value={'revenue'}>Revenue</MenuItem>
			<MenuItem value={'profit'}>Profit</MenuItem>
		</Select>
		<a href="/PS/api/ftr/transactions.csv" target='_blank'>
			Export
		</a>
	</>
);
