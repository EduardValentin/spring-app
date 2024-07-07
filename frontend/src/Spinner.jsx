import ReactDOM from 'react-dom';
import React from 'react';
import { CircularProgress } from '@material-ui/core';

export const Spinner = ({ spinning }) =>
	ReactDOM.createPortal(
		<>{spinning ? <CircularProgress size={100} className="spinner" /> : <div />}</>,
		document.getElementById('modal-root')
	);
