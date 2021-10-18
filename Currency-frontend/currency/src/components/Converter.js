import React, {useState, useEffect} from 'react'
import Select from './Select'
import Styled from 'styled-components'
import Arrow from '../resources/arrow.png';

const Converter = (props) => {

    const [sellCurrencyCode, setSellCurrencyCode] = useState("EUR")
    const [buyCurrencyCode, setBuyCurrencyCode] = useState("EUR")
    const [amountSellCurrency, setAmountSellCurrency] = useState(1)
    const [amountBuyCurrency, setAmountBuyCurrency] = useState(1)

    const [buyRate, setBuyRate] = useState()

    const handleChangeSellCurrencyCode = (newCurrency) => {
        setSellCurrencyCode(newCurrency)
      }

    const handleChangeBuyCurrencyCode = (newCurrency) => {
        setBuyCurrencyCode(newCurrency)
    }

    const handleSellAmountChange = (newAmount) => {
        setAmountSellCurrency(newAmount)
    }

    const handleBuyAmountChange = (newAmount) => {
        setAmountBuyCurrency(newAmount)
    }

    const convertSellToBuy = () => {

        const sellingRate =
            sellCurrencyCode === "EUR" ? 1 : findExchangeRate(sellCurrencyCode)

        const valueInEur = amountSellCurrency / sellingRate;
        const buyRate = buyCurrencyCode === "EUR" ? 1 : findExchangeRate(buyCurrencyCode)
        setBuyRate(buyRate) 

        const roundValue = Number(valueInEur * buyRate).toFixed(3)
        setAmountBuyCurrency(roundValue)
      }
    
      const convertBuyToSell = () => {

        const buyRate = buyCurrencyCode === "EUR" ? 1 : findExchangeRate(buyCurrencyCode) 

        const valueInEur = amountBuyCurrency / buyRate

        const sellRate = sellCurrencyCode === "EUR" ? 1 : findExchangeRate(sellCurrencyCode)

        const roundValue = Number(valueInEur * sellRate).toFixed(3)
        setAmountSellCurrency(roundValue)
      };

      const findExchangeRate = (currencyCodeValue) => {
        return props.data
            .filter(currency => currency.currencyCode === currencyCodeValue)
            .map(currency => currency.currencyRate)
      }
    
      useEffect(() => {
        convertSellToBuy()
      }, [amountSellCurrency, buyCurrencyCode]);
    
      useEffect(() => {
        convertBuyToSell()
      }, [amountBuyCurrency, sellCurrencyCode]);

    
    return(
        <FormWrapper onSubmit={props.handleSubmit}>
            <Select 
                data={props.codes} 
                currencyCode={sellCurrencyCode}
                currencyValue={amountSellCurrency} 
                onCodeChange={handleChangeSellCurrencyCode}
                onAmountChange={handleSellAmountChange}
            />
            <Logo src={Arrow}/>
            <Select 
                data={props.codes} 
                currencyCode={buyCurrencyCode} 
                currencyValue={amountBuyCurrency}
                onCodeChange={handleChangeBuyCurrencyCode}
                onAmountChange={handleBuyAmountChange} 
                exchangeRate={buyRate}
            />
        </FormWrapper>
    )
}

export default Converter;

const FormWrapper = Styled.form`
    display: inline-block;
`
const Logo = Styled.img`
    width: 5%;
`;