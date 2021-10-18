import React from 'react'


const Buttons = (props) => {

    return (
        <div onChange={props.handleChange}>
        <label>
            <input
                type="radio"
                value="month"
                checked={props.term === "month"}
            />
            1 month
        </label>
        <label>
            <input
                type="radio"
                value="weeks"
                checked={props.term === "weeks"}
            />
            2 weeks
        </label>
        <label>
            <input
                type="radio"
                value="week"
                checked={props.term === "week"}
            />
            1 week
        </label>
    </div>
    )


}

export default Buttons