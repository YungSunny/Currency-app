import React from 'react';
import { Line } from 'react-chartjs-2';

const LineChar = (props) => {

    const date = props.data.map(item => item.date)

    const currencyRate = props.data.map(item => item.currencyRate)

    const code = props.data.map(item => item.currencyCode)
       
    return(
        <div>
        <Line
            data={{
            labels: date,
            datasets: [
                {
                label: 'Kurso pokytis ' + code[0],
                data: currencyRate,
                borderColor: [
                    'rgba(0, 0, 0, 1)',
                ],
                pointBorderColor: [
                    'rgba(255, 0, 0, 1)'
                ],
                borderWidth: 1,
                },
            ],
            }}
            height={400}
            width={300}
            options={{
            maintainAspectRatio: false,
            scales: {
                yAxes: [
                {
                    ticks: {
                    beginAtZero: true,
                    },
                },
                ],
            },
            legend: {
                labels: {
                fontSize: 25,
                },
            },
            }}
        />
    </div>
    )
}

export default LineChar;