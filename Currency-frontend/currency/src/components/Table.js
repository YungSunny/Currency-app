import React, {useState, useEffect} from 'react'
import Styled from 'styled-components';
import { Table, Thead, Tbody, Tr, Th, Td } from 'react-super-responsive-table'
import LineChart from './LineChart';
import Buttons from './Buttons';
import 'react-super-responsive-table/dist/SuperResponsiveTableStyle.css'

const TableCom = (props) => {

    const [currencyCode, setCurrencyCode] = useState()
    const [currencyData, setCurrencyData] = useState([]);

    const [currencyTerm, setCurrencyTerm] = useState("month")

    const fetchRates = async () => {
        const response = await fetch("http://localhost:8080/currency/api/get/" + currencyTerm + "/" + currencyCode);
        const result = await response.json();
        setCurrencyData(result)
    }

    useEffect(() => {
        setCurrencyCode("USD")
    }, [])

    useEffect(() => {
        fetchRates();
    }, [currencyCode, currencyTerm])


    const data = props.data.map(item => {
        return( 
            <Tr key={item.currencyCode}>
                <Tds onClick={() => setCurrencyCode(item.currencyCode)}>{item.currencyCode}</Tds>
                <Tds>{item.currencyRate}</Tds>
            </Tr>
        )
    })

    const handleChange = (e) => {
        setCurrencyTerm(e.target.value)
    }

    return(
        <TableWrapper>
            <Table>
                <Thead>
                    <Tr>
                        <Ths>Valiutos kodas</Ths>
                        <Ths>Kursas</Ths>
                    </Tr>
                </Thead>
                <Tbody>
                    {data}
                </Tbody>
            </Table>
           <Buttons
                term={currencyTerm}
                handleChange={handleChange}
           />
            <LineChart
                data={currencyData}
            />
        </TableWrapper>

    )
}

export default TableCom;

const TableWrapper = Styled.div`
    width: 80%;
    margin: 0 auto;
    text-align: center;
    border-style: solid;
    margin-bottom: 15px;
`
const Tds = Styled(Td)`
    border-bottom:  1px solid;
    border-right: 1px solid;
`
const Ths = Styled(Th)`
    border-bottom: solid;
`