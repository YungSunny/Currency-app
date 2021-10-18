import React from 'react';

const Select = (props) => {

    const selectOptions = props.data
        .map(item => {
            return(
                <option key={item} value={item}>{item}</option>
            )
    })

    const handleCodeChange = (e) => {
        console.log(e)
        const {value} = e.target;
        props.onCodeChange(value);
    }

    const handleAmountChange = (e) => {

        const {value} = e.target;
        props.onAmountChange(value);
    }

    return(
        <div>
            <select value={props.currencyCode} onChange={handleCodeChange}>
                {selectOptions}  
            </select>
            <input type="number" value={props.currencyValue} onChange={handleAmountChange}/>
                <div>
            {props.exchangeRate ? "1 EUR = " + props.exchangeRate + " " + props.currencyCode : null}
                </div>
        </div>  
    )
}

export default Select;

